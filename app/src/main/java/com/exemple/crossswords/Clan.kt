import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Clan")
class Clan {
    @Element(name = "name")
    private val name: String? = null

    @Element(name = "clanColorA")
    private val clanColorA: String? = null

    @Element(name = "clanColorB")
    private val clanColorB: String? = null

    @Element(name = "clanIcon")
    private val clanIcon: String? = null // Getters e Setters
}

