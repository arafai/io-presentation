
//ContextShift is the pure equivalent to Scala’s ExecutionContext
trait ContextShift[F[_]] {

  def shift: F[Unit]

  def evalOn[A](ec: ExecutionContext)(f: F[A]): F[A]
}

def fib(n: Int, a: Long = 0, b: Long = 1)
        (implicit cs: ContextShift[IO]): IO[Long] = {

  IO.suspend {
    val next =
      if (n > 0) fib(n - 1, b, a + b)
      else IO.pure(a)

    // Triggering a logical fork every 100 iterations
    if (n % 100 == 0)
    //        cs.shift *> next
      IO.shift(cs).flatMap(_ => next)
    else
      next
  }
}

def blockingThreadPool: Resource[IO, ExecutionContext] =
  Resource(IO.delay {
    val executor = Executors.newCachedThreadPool()
    val ec = ExecutionContext.fromExecutor(executor)
    (ec,IO.delay(executor.shutdown()))
  })

def readName: IO[String] =
  IO.delay {
    println("Enter your name: ")
    scala.io.StdIn.readLine()
  }

object MyApp extends IOApp {

  def run(args: List[String]) = {
    val name = blockingThreadPool.use { ec =>
// Blocking operation, executed on special thread-pool
      contextShift.evalOn(ec)(readName)
    }

    for {
      n <- name
      _ <- IO(println(s"Hello, $n!"))
    } yield ExitCode.Success
  }
}