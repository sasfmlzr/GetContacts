package contact.architecture

abstract class Result {

    override fun toString() = javaClass.simpleName

    override fun equals(other: Any?): Boolean =
            super.equals(other) || this.javaClass == other?.javaClass

    override fun hashCode() = javaClass.hashCode()

}
