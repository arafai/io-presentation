val r = new Random(0L)

for {
  randomOne <- Future(r.nextInt)
  randomTwo <- Future(r.nextInt)
} yield (randomOne, randomTwo)

// refactoring
val future = Future(r.nextInt)
for {
  randomOne <- future
  randomTwo <- future
} yield (randomOne, randomTwo)