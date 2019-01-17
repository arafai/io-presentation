## Writing purely functional code using IO


---


@title[Referential transparency]
## Referential transparency
<br/>
<br/>
<br/>

#### An expression is called **referentially transparent** if it can be replaced with its corresponding value without changing the program's behavior.

---
@title[Referential transparency 2]

+++?code=src/main/scala/Future.scala&lang=scala&title=Future

@[1-6]
@[9-13]

---
@title[Referential transparency 2]

+++?code=src/main/scala/IO_RF.scala&lang=scala&title=IO

@[1-6]
@[9-13]
---
@size[1.5em](A value of type IO[A] is a computation which, when evaluated, can perform effects before returning a value of type A.)
<br/>
@size[1.5em](IO values are pure, immutable values and thus preserves referential transparency, being usable in functional programming.)
<br/>
@size[1.5em](An IO is a data structure that represents just a description of a side effectful computation.)


---

IO can describe synchronous or asynchronous computations that:
* on evaluation yield exactly one result
* can end in either success or failure and in case of failure flatMap chains get short-circuited (IO implementing the algebra of MonadError)
* can be canceled, but note this capability relies on the user to provide cancellation logic
