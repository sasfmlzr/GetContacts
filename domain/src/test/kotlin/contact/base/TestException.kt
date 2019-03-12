package contact.base

internal class TestException : RuntimeException() {

    override fun equals(other: Any?) = other is TestException && other.message == message

    override fun hashCode() = message?.hashCode() ?: -1

}
