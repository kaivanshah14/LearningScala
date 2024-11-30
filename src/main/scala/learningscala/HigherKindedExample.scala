package learningscala

trait Container[F[_]]{
  def wrap[T](value: F[T]): F[F[T]]
}

object ListContainer extends Container[List]{
  override def wrap[T](value: List[T]): List[List[T]] = List(value) // Here we are converting a List to a List[List[T]], this is the usecase of Higher Kinded Types
}

object HigherKindedExample extends App {
  val list = List(1,2,3,4,5)
  val wrappedList = ListContainer.wrap(list)
  println(wrappedList) // Output: List(List(1,2,3,4,5)) The original list is wrapped in another list.

  val option = Option(42)
  val wrappedOption = OptionContainer.wrap(option)
  println(wrappedOption) // Output: Some(Some(42))

  // Example with empty Option
  val emptyOption = Option.empty[Int]
  val wrappedEmptyOption = OptionContainer.wrap(emptyOption)
  println(wrappedEmptyOption) // Output: Some(None)
}

// Another Example using Option:

object OptionContainer extends Container[Option] {
  // Wrap an Option[T] into an Option[Option[T]]
  override def wrap[T](value: Option[T]): Option[Option[T]] = Some(value)
}



