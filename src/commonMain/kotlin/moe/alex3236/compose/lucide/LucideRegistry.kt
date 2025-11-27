package moe.alex3236.compose.lucide

import androidx.compose.ui.graphics.vector.ImageVector
import moe.alex3236.compose.lucide.lucide.SquareSlash

data class IconMetadata(
    val name: String,
    val tags: List<String>,
    val categories: List<String>,
) {
    val icon: ImageVector by lazy {
        Lucide.AllIcons.find { it.name == name } ?: Lucide.SquareSlash
    }
}

@Suppress("unused")
object LucideRegistry {

    /**
     * Lazily initializes a list of all `IconMetadata` objects.
     */
    val allMeta: List<IconMetadata> by lazy {
        allMetadata
    }

    /**
     * Lazily initializes a map of `IconMetadata` objects, keyed by their name.
     */
    val metaByName: Map<String, IconMetadata> by lazy {
        allMeta.associateBy { it.name }
    }

    /**
     * Filters and returns a list of `IconMetadata` objects whose tags contain the given query string.
     *
     * @param query The tag query string to search for.
     * @return A list of matching `IconMetadata` objects.
     */
    fun metaByTag(query: String): List<IconMetadata> {
        val lowerQuery = query.lowercase()
        return allMeta.filter { it.tags.any { tag -> tag.lowercase().contains(lowerQuery) } }
    }

    /**
     * Filters and returns a list of `IconMetadata` objects whose categories contain the given query string.
     *
     * @param query The category query string to search for.
     * @return A list of matching `IconMetadata` objects.
     */
    fun metaByCategory(query: String): List<IconMetadata> {
        val lowerQuery = query.lowercase()
        return allMeta.filter { it.categories.any { category -> category.lowercase().contains(lowerQuery) } }
    }

    /**
     * Filters and returns a list of `IconMetadata` objects whose names contain the given query string.
     *
     * @param query The name query string to search for.
     * @return A list of matching `IconMetadata` objects.
     */
    fun metaByName(query: String): List<IconMetadata> {
        val lowerQuery = query.lowercase()
        return allMeta.filter { it.name.lowercase().contains(lowerQuery) }
    }

    /**
     * Searches for `IconMetadata` objects that match the given query string in their name, tags, or categories.
     *
     * @param query The query string to search for.
     * @return A list of matching `IconMetadata` objects.
     */
    fun searchMeta(query: String): List<IconMetadata> {
        val lowerQuery = query.lowercase()
        return allMeta.filter {
            it.name.lowercase().contains(lowerQuery) ||
                    it.tags.any { tag -> tag.lowercase().contains(lowerQuery) } ||
                    it.categories.any { category -> category.lowercase().contains(lowerQuery) }
        }
    }

    /**
     * Retrieves the `IconMetadata` object for the given name, or `null` if not found.
     *
     * @param name The name of the icon metadata to retrieve.
     * @return The matching `IconMetadata` object, or `null` if not found.
     */
    fun getMetadata(name: String): IconMetadata? {
        return metaByName.getOrElse(name) { null }
    }

    /**
     * Retrieves a list of `ImageVector` objects for icons whose tags match the given query string.
     *
     * @param query The tag query string to search for.
     * @return A list of matching `ImageVector` objects.
     */
    fun iconByTag(query: String): List<ImageVector> {
        return metaByTag(query).map { it.icon }
    }

    /**
     * Retrieves a list of `ImageVector` objects for icons whose categories match the given query string.
     *
     * @param query The category query string to search for.
     * @return A list of matching `ImageVector` objects.
     */
    fun iconByCategory(query: String): List<ImageVector> {
        return metaByCategory(query).map { it.icon }
    }

    /**
     * Retrieves a list of `ImageVector` objects for icons whose names match the given query string.
     *
     * @param query The name query string to search for.
     * @return A list of matching `ImageVector` objects.
     */
    fun iconByName(query: String): List<ImageVector> {
        return metaByName(query).map { it.icon }
    }

    /**
     * Searches for `ImageVector` objects that match the given query string in their name, tags, or categories.
     *
     * @param query The query string to search for.
     * @return A list of matching `ImageVector` objects.
     */
    fun searchIcon(query: String): List<ImageVector> {
        return searchMeta(query).map { it.icon }
    }
}
