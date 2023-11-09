package Section_3

object Enums extends App
{
    enum Permissions
    {
        case READ, WRITE, EXECUTE, NONE

        // add fields or methods
        def openDocument(): Unit =
        {
            if(this == READ) println("opening document...")
            else println("reading not allowed...")
        }
    }

    val somePermissions: Permissions = Permissions.READ
    somePermissions.openDocument()

    // constructor arguments
    enum PermissionsWithBits(bits: Int)
    {
        case READ extends PermissionsWithBits(4)
        case WRITE extends PermissionsWithBits(2)
        case EXECUTE extends PermissionsWithBits(1)
        case NONE extends PermissionsWithBits(0)
    }

    object PermissionsWithBits
    {
        def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
    }

    // standard Api
    val somePermissionsOrdinal: Int = somePermissions.ordinal
    val allPermissions: Any = PermissionsWithBits.values
    val readPermissions: Permissions = Permissions.valueOf("READ")
}