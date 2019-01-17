## Writing purely functional code using IO

---

@title[Referential transparency]
## Referential transparency
<br/>
<br/>

#### An expression is called **referentially transparent** if it can be replaced with its corresponding value without changing the program's behavior.

---
@title[Referential transparency 2]

Referential transparency and Scala Future

+++?code=src/main/scala/Future.scala&lang=scala&title=Future

@[1-6]
@[9-13]

---
@title[Referential transparency 2]

Referential transparency and IO

+++?code=src/main/scala/IO_RF.scala&lang=scala&title=IO

@[1-6]
@[9-13]
---
@size[1.0em](A value of type IO[A] is a computation which, when evaluated, can perform effects before returning a value of type A)
<br/>
@size[1.0em](IO values are pure, immutable values and thus preserves referential transparency, being usable in functional programming)
<br/>
@size[1.0em](An IO is a data structure that represents just a description of a side effectful computation)


---

IO can describe synchronous or asynchronous computations that:
* @size[1.0em](on evaluation yield exactly one result)
* @size[1.0em](can end in either success or failure and in case of failure flatMap chains get short-circuited)
* @size[1.0em](can be canceled, but note this capability relies on the user to provide cancellation logic)

---

### Quick tour of IO - part 1
+++?code=src/main/scala/IO.scala&lang=scala&title=IO

@[1-5]
@[8-13]
@[16-27]
@[29-42]

---

### Quick tour of IO - part 2
+++?code=src/main/scala/IO_2.scala&lang=scala&title=IO

@[2-8]
@[10-25]
@[27-38]
@[40-53]

---

### Quick tour of IO - part 3
+++?code=src/main/scala/IO_3.scala&lang=scala&title=IO

@[1-15]
@[17-30]
@[23-37]
