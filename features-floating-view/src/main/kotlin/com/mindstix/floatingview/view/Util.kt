package com.mindstix.floatingview.view

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mindstix.core.logger.Logger
import com.mindstix.core.utils.EMPTY_STRING

data class Product(
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val price: String = EMPTY_STRING,
    val productImageUrl: String = EMPTY_STRING,
    val detailUrl: String = EMPTY_STRING,
)

data class ProductCategories(
    val perfume: List<Product>,
    val skin_care: List<Product>,
    val lipsticks: List<Product>
)

/*
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
*/


const val productData = """
{
"perfume": [
{
"name": "Perfume Live Air",
"description": "L'BEL USA SKU: 13959",
"price": "${'$'}35",
"productImageUrl": "https://lbelmy.store/cdn/shop/files/Perfume-Live-Air-L_BEL-USA-_-ESIKA-4657958_1800x1800.jpg?v=1705892305",
"detailUrl": "https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/perfume-live-air"
},
{
"name": "L'bel Bleu Intense Perfume",
"description": "L'BEL USA SKU: 02905",
"price": "${'$'}29",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/bleu-intense-lbel-perfume-hombre-precio-catalogo-lbel-usa-lbel-amazon-estados-unidos_1800x1800.jpg?v=1665169761",
"detailUrl": "https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-bleu-intense"
},
{
"name": "L'bel New Code Red Perfume",
"description": "L'BEL USA SKU: 12345",
"price": "${'$'}45",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/l_bel-estados-unidos-perfume-hombre-new-code-red-promociones-free-shipping-tienda-online-l_bel-esika-usa_6364f183-d474-409f-bcb8-d7f1479ed697_1800x1800.jpg?v=1652573696",
"detailUrl": "https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-new-code-red"
},
{
"name": "L'bel Homme 033 Perfume",
"description": "L'BEL USASKU: 95030",
"price": "${'$'}39",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/homme-033-perfume-hombre-lbel-compralo-precio-catalogo-tienda-online-lbel-esika-usa-comprar-cyzone-productos-lbel-idaho-michigan-florida-alabama_166f7cee-2d36-454a-808f-4209a4895dd4_1800x1800.jpg?v=1652573376",
"detailUrl": "https://lbelmy.store/en/collections/perfumes-de-hombre-lbel-usa/products/lbel-homme-033"
}
],
"skin_care": [
{
"name": "L'bel Concentre Total Anti-aging Treatment",
"description": "L'BEL USA SKU: 13224",
"price": "${'$'}76",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/lbel-concentre-total-crema-antiedad-todo-tipo-de-piel-formula-francesa-tienda-online-chicago-illinois-estados-unidos-lbel-amazon-dibenisa-913746_a050a2a4-4108-4b3b-b5eb-a4f63ebc80af_1800x1800.jpg?v=1652573226",
"detailUrl": "https://lbelmy.store/en/products/lbel-concentre-total?_pos=2&_sid=f41b4f273&_ss=r"
},
{
"name": "L'bel Concentre Total Ultra Firm Renewing Serum",
"description": "L'BEL USA SKU: 16740",
"price": "${'$'}79",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/lbel-concentre-total-suero-facial-serum-antiedad-para-que-edad-es-precio-tienda-online-lbel-usa-dibenisa-envios-maryland-rhode-island-new-jersey_8cf5e64e-4424-4f95-85f2-b55db56a8b63_1800x1800.jpg?v=1652573223",
"detailUrl": "https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-concentre-total-ultra-firm"
},
{
"name": "Lbel Nocturne Facial Moisturizer",
"description": "L'BEL USA SKU: 56522",
"price": "${'$'}22",
"productImageUrl": "https://lbelmy.store/cdn/shop/products/WXbicey_449787c2-c207-4a7e-86c2-030f88330df1_1800x1800.jpg?v=1653837832",
"detailUrl": "https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-nocturne-hidratante-facial"
},
{
"name": "L'bel Bio Resist Nourishing Vegan Moisturizing Cream",
"description": "L'BEL USA SKU: 04071",
"price": "${'$'}43",
"productImageUrl": "https://lbelmy.store/cdn/shop/files/l_bel_amazon_bio_resist_crema_nutritiva_usa_Comprar_estados_unidos_1800x1800.jpg?v=1684025091\n",
"detailUrl": "https://lbelmy.store/en/collections/tratamiento-facial-lbel/products/lbel-bio-resist-nutritiva"
}
],
"lipsticks": [
{
"name": "Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration",
"description": "Esika SKU: 19130",
"price": "${'$'}9",
"productImageUrl": "",
"detailUrl": "https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-pimienta-caliente?_pos=1&_sid=f38a8c0be&_ss=r"
},
{
"name": "Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration",
"description": "Esika SKU: 19456",
"price": "${'$'}9",
"productImageUrl": "https://lbelmy.store/cdn/shop/files/Esika-Colorfix-Duo-Tattoo-FUCSIA-AUTENTICA-ESIKA-USA-4715530_1800x1800.jpg?v=1707342763",
"detailUrl": "https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-fucsia-autentica?_pos=3&_sid=f38a8c0be&_ss=r"
},
{
"name": "Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration",
"description": "Esika SKU: 19123",
"price": "${'$'}9",
"productImageUrl": "https://lbelmy.store/cdn/shop/files/Esika-Colorfix-Duo-Tattoo-VINO-ACTITUD-ESIKA-4607021_1800x1800.jpg?v=1704509755",
"detailUrl": "https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-vino-actitud?_pos=10&_sid=f38a8c0be&_ss=r"
},
{
"name": "Esika Duo Tattoo Intense Lipstick 12 Hrs. Duration",
"description": "Esika SKU: 19129",
"price": "${'$'}9",
"productImageUrl": "https://lbelmy.store/cdn/shop/files/Esika-Colorfix-Duo-Tattoo-ROSA-ROMANCE-ESIKA-4606997_1800x1800.jpg?v=1704509749",
"detailUrl": "https://lbelmy.store/en/products/esika-colorfix-duo-tattoo-rosa-romance?_pos=7&_sid=f38a8c0be&_ss=r"
}
]
}
"""

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
        Logger.d {"##### searchWords $searchWords"}
        Logger.d {"##### allProducts $allProducts"}
        val response =  allProducts.find { product ->
            searchWords.any { word ->
                product.name.contains(word.replace(",", ""), ignoreCase = true) ||
                        product.description.contains(word, ignoreCase = true)
            }
        }
        return response

    } catch (e: JsonSyntaxException) {
        println("Invalid JSON format: ${e.message}")
    }
    return null
}

fun getProductsList(): List<Product> {
    val gson = Gson()
    val products = gson.fromJson(productData, ProductCategories::class.java)
    // Searching across all categories

    return products.perfume + products.skin_care + products.lipsticks
}
