## Writing purely functional code using IO


---


@title[Referential transparency]
## Referential transparency


#### An expression is called **referentially transparent** if it can be replaced with its corresponding value without changing the program's behavior.

---
@title[Referential transparency 2]

#### Breaking referentially transparency

+++?code=src/main/scala/Future.scala&lang=scala&title=Future

@[1-6]
@[9-13]

---
@title[Referential transparency 2]

#### Breaking referentially transparency

+++?code=src/main/scala/IO_RF.scala&lang=scala&title=IO

@[1-6]
@[9-13]
