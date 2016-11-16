package co.com.ach.soi.data

import org.scalatest.{ Matchers, WordSpecLike }
import co.com.ach.soi.data.RowParser._

class RowParserSpec extends Matchers with WordSpecLike {

  "A row parser " should {
    "parsear" in {
      val person = personParser("Amy, 54.2")
      person.name should be("Amy")

    }
  }

  "A safe row parser " should {
    import co.com.ach.soi.data.SafeRowParser._
    "parsear" in {
      val person = personParser("Amy, 54.2")
      person.get.name should be("Amy")

    }

    "error parsear book" in {
      val book = bookParser("Hamlet, Shakespeare")
      book should be(None)

    }

    "parsear to person with apply method and implicit parser" in {
      val person = SafeRowParser[Person]("Amy, 54.2")
      person.get.name should be("Amy")

    }

    "parsear to book with apply method and implicit parser" in {
      val book = SafeRowParser[Book]("Hamlet, Shakespeare")
      book should be(None)

    }
  }

}
