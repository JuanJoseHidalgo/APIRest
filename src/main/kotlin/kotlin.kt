import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import javax.swing.plaf.synth.Region

@Serializable
data class Ipaddress(
    val ip: String,
    val city: String,
    val region: String,
    val country: String,
    val loc: String,
    val org: String,
    val postal: String,
    val timezone: String,
)

fun main() {

    //  crear cliente http
    val client = HttpClient.newHttpClient()
    print("Introduce unha ip pública: ")
    val ip=readln().toString()
    val todo="https://ipinfo.io/"+ip+"/json?token=437bf12a0fd22b"

    // crear solicitud
    val request = HttpRequest.newBuilder()
        .uri(URI.create(todo))
        .GET()
        .build()

    //  Enviar la solicitud con el cliente
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    // obtener string con datos
    val jsonBody = response.body()


    val ipAddressInfo: Ipaddress = Json.decodeFromString(jsonBody)

    // imprimir datos en formato legible
    println("O enderezo IP: ${ipAddressInfo.ip}")
    println("Corresponde á seguinte cidade: ${ipAddressInfo.city}")
    println("Que está na rexion de: ${ipAddressInfo.region}")
    println("Ubicada no seguinte pais: ${ipAddressInfo.country}")
    println("As súas coordenadas GPS son: ${ipAddressInfo.loc}")
    println("Corresponde á seguinte compañía telefónica: ${ipAddressInfo.org}")
    println("Situada no seguinte código postal: ${ipAddressInfo.postal}")
    println("Está na seguinte zona horaria: ${ipAddressInfo.timezone}")
}