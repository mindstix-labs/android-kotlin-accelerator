package com.mindstix.floatingview.view

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mindstix.core.logger.Logger
import com.mindstix.core.utils.EMPTY_STRING

data class Product(
    val name: String = EMPTY_STRING,
    val description: String= EMPTY_STRING,
    val price: String= EMPTY_STRING,
    val productImageUrl: String= EMPTY_STRING,
    val detailUrl: String= EMPTY_STRING,
)

data class ProductCategories(
    val perfume: List<Product>,
    val skin_care: List<Product>,
    val lipsticks: List<Product>
)

const val productData =
    "{\n" +
            "  \"perfume\": [\n" +
            "    {\n" +
            "      \"name\": \"Perfume Live Air\",\n" +
            "      \"description\": \"L'BEL USA SKU: 13959\",\n" +
            "      \"price\": \"\$35\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/perfume-live-air\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"L'bel Bleu Intense Perfume\",\n" +
            "      \"description\": \"L'BEL USA SKU: 02905\",\n" +
            "      \"price\": \"\$29\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-bleu-intense\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"L'bel New Code Red Perfume\",\n" +
            "      \"description\": \"L'BEL USA SKU: 12345\",\n" +
            "      \"price\": \"\$45\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-new-code-red\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"L'bel Homme 033 Perfume\",\n" +
            "      \"description\": \"L'BEL USA SKU: 95030\",\n" +
            "      \"price\": \"\$39\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-homme-033\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"skin_care\": [\n" +
            "    {\n" +
            "      \"name\": \"L'bel Concentre Total Anti-aging Treatment\",\n" +
            "      \"description\": \"L'BEL USA SKU: 13224\",\n" +
            "      \"price\": \"\$76\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/products/lbel-concentre-total?_pos=2&_sid=f41b4f273&_ss=r\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"L'bel Concentre Total Ultra Firm Renewing Serum\",\n" +
            "      \"description\": \"L'BEL USA SKU: 16740\",\n" +
            "      \"price\": \"\$79\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-concentre-total-ultra-firm\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Lbel Nocturne Facial Moisturizer\",\n" +
            "      \"description\": \"L'BEL USA SKU: 56522\",\n" +
            "      \"price\": \"\$22\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-nocturne-hidratante-facial\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"L'bel Bio Resist Nourishing Vegan Moisturizing Cream\",\n" +
            "      \"description\": \"L'BEL USA SKU: 04071\",\n" +
            "      \"price\": \"\$43\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-bio-resist-nutritiva\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"lipsticks\": [\n" +
            "    {\n" +
            "      \"name\": \"Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration\",\n" +
            "      \"description\": \"Esika SKU: 19130\",\n" +
            "      \"price\": \"\$9\",\n" +
            "      \"color\": \"Hot Pepper Red\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-pimienta-caliente?_pos=1&_sid=f38a8c0be&_ss=r\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration\",\n" +
            "      \"description\": \"Esika SKU: 19456\",\n" +
            "      \"price\": \"\$9\",\n" +
            "      \"color\": \"Fuchsia\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-fucsia-autentica?_pos=3&_sid=f38a8c0be&_ss=r\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration\",\n" +
            "      \"description\": \"Esika SKU: 19123\",\n" +
            "      \"price\": \"\$9\",\n" +
            "      \"color\": \"Wine\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-vino-actitud?_pos=10&_sid=f38a8c0be&_ss=r\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration\",\n" +
            "      \"description\": \"Esika SKU: 19129\",\n" +
            "      \"price\": \"\$9\",\n" +
            "      \"color\": \"Romantic Pink\",\n" +
            "      \"detailUrl\": \"https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-rosa-romance?_pos=7&_sid=f38a8c0be&_ss=r\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"



fun searchProduct(
    searchTerm: String,
): Product? {
    try {
        val gson = Gson()
        val products = gson.fromJson(productData, ProductCategories::class.java)
        // Searching across all categories

        val allProducts = products.perfume + products.skin_care + products.lipsticks

        // Split the search term into separate words
        val searchWords = searchTerm.split(" ")

        // Find the first product that contains all the search terms in either name or description
        Logger.d {"##### allProducts $allProducts"}

        val response =  allProducts.find { product ->
            searchWords.all { word ->
                product.name.contains(word, ignoreCase = true) ||
                        product.description.contains(word, ignoreCase = true)
            }
        }
        Logger.d {"##### suggested $response"}
        return response

    } catch (e: JsonSyntaxException) {
        println("Invalid JSON format: ${e.message}")
    }
    return null
}