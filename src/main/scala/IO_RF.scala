val r = new Random(0L)

for {
  randomOne <- IO(r.nextInt)
  randomTwo <- IO(r.nextInt)
} yield (randomOne, randomTwo)

// refactoring
val io = IO(r.nextInt)
for {
  randomOne <- io
  randomTwo <- io
} yield (randomOne, randomTwo)