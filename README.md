# Lucide Compose

![Jitpack Status](https://jitpack.io/v/alex3236/lucide-compose.svg)

Brings [Lucide Icons](https://lucide.dev/) to Jetpack Compose.

> [!NOTE]
> This is my very first Compose project. Tell me if you find anything I missed or could be improved!

## Usage

```groovy
repositories {
    maven { url = uri("https://jitpack.io") }
}
```

```kotlin
dependencies {
    implementation("com.github.alex3236:lucide-compose:<version>")
}
```

```kotlin
@Composable
@Preview
fun IconTest() {
    Grid(
        columns = 4,
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector = Lucide.Info)
        LucideRegistry.searchIcon("buildings").forEach {
            Icon(imageVector = it)
        }
        LucideRegistry.iconByCategory("home").forEach {
            Icon(imageVector = it)
        }
    }
}
```
