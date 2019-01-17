//Pure Values — IO.pure & IO.unit

def pure[A](a: A): IO[A] = ???

IO.pure(25).flatMap(n => IO(println(s"Number is: $n")))


// Synchronous Effects -  IO.apply

def apply[A](body: => A): IO[A] = ???

val readLn = IO(scala.io.StdIn.readLine)


// Asynchronous Effects — IO.async & IO.cancelable
def async[A](k: (Either[Throwable, A] => Unit) => Unit): IO[A] = ???

def convert[A](fa: => Future[A])(implicit ec: ExecutionContext): IO[A] =
  IO.async { cb =>
    // This triggers evaluation of the by-name param and of onComplete,
    // so it's OK to have side effects in this callback
    fa.onComplete {
      case Success(a) => cb(Right(a))
      case Failure(e) => cb(Left(e))
    }
  }

def cancelable[A](k: (Either[Throwable, A] => Unit) => IO[Unit]): IO[A] = ???

def delayedTick(d: FiniteDuration)
               (implicit sc: ScheduledExecutorService): IO[Unit] = {

  IO.cancelable { cb =>
    val r = new Runnable { def run() = cb(Right(())) }
    val f = sc.schedule(r, d.length, d.unit)

    // Returning the cancellation token needed to cancel
    // the scheduling and release resources early
    IO(f.cancel(false))
  }
}