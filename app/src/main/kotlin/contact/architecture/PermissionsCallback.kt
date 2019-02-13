package contact.architecture

interface PermissionsCallback {
    fun onPermissionsGranted(permissions: List<String>)
}