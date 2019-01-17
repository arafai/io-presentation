## Writing purely functional code using IO


---


@title[Referential transparency]

An expression is called **referentially transparent** if it can be replaced with
its corresponding value without changing the program's behavior.

---
@title[Referential transparency 2]

```scala
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

```
