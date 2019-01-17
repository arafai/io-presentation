// bracket
trait Bracket[F[_], E] extends MonadError[F, E] {

  // Simpler version, doesn't distinguish b/t exit conditions
  def bracket[A, B](acquire: F[A])(use: A => F[B])
                   (release: A => F[Unit]): F[B]
}

// Resource -  Effectfully allocates and releases a resource.
abstract class Resource[F[_], A] {
  def allocate: F[(A, F[Unit])]

  def use[B, E](f: A => F[B])(implicit F: Bracket[F, E]): F[B] =
    F.bracket(allocate)(a => f(a._1))(_._2)
}

def mkResource(s: String): Resource[IO, String] = {
  val acquire: IO[String] =
    IO(println(s"Acquiring $s")).flatMap(_ => IO.pure(s))

  def release(s: String): IO[Unit] = IO(println(s"Releasing $s"))

  Resource.make(acquire)(release)
}

val r = for {
  outer <- mkResource("outer")
  inner <- mkResource("inner")
} yield (outer, inner)

r.use { case (a, b) => IO(println(s"Using $a and $b")) }
 .unsafeRunSync


val acquire = IO {
  scala.io.Source.fromString("Hello world")
}

Resource
  .fromAutoCloseable(acquire)
  .use(source => IO(println(source.mkString)))
  .unsafeRunSync()