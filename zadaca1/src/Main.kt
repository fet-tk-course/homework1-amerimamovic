fun main() {
    val inzenjeri = listOf<inzenjer>(
        softverskiInzenjer("Edin", "Hodžić","dipl. ing. softverski inženjer", 3, listOf("Java", "Spring"), 6),
        softverskiInzenjer("Ensar", "Ćehajić","dipl. ing. softverski inženjer", 12, listOf("Kotlin", "Cloud", "Docker"), 24),
        inzenjerElektrotehnike("Ajdin", "Herčinović","diplomirani inženjer elektrotehnike", 6, listOf("Analogna elektronika", "PCB"), 4),
        inzenjerElektrotehnike("Irmel", "Haskić","diplomirani inženjer elektrotehnike", 10, listOf("DSP", "Embedded", "C"), 7),
        inzenjerElektrotehnike("Amer", "Imamovic", "diplomirani inženjer elektrotehnike",8, listOf("Kotlin", "Android", "SQL"), 15)
    )
    // Pozvati sve funkcionalnosti
    println(inzenjeri[2].identitet())
    println(inzenjeri[4].profesionalnaTitula())
    println(inzenjeri[1].osnovneInformacije())


    println("Grupisanje inženjere po ekspertizama koji imaju vise od 5 godina iskustva")
    val foo = grupisanjeEkspertize(inzenjeri)
    for ((i, lista) in foo) {
        println("Ekspertiza: $i")
        for (l in lista) println("  ${l.identitet()}")
    }

    println("Najiskusniji inženjer u svakom tipu")
    val najiskusniji = najiskusniji(inzenjeri)
    for ((k, v) in najiskusniji) {
        println("$k -> ${v.identitet()} (${v.godineIskustva} godina)")
    }

    println("Zbir projekata i certifikata svih inženjera")
    val ukupno = ukupnoProjekataICertifikata(inzenjeri)
    println("Ukupan broj : $ukupno")
}

interface osoba{
    fun identitet() : String
    fun profesionalnaTitula() : String
}

open class inzenjer(
    var ime : String,
    var prezime : String,
    var titula : String,
    var godineIskustva : Int,
    val ekspertize : List<String>
) : osoba {
    init
    {
        if(ime.isBlank())
        {
            throw IllegalArgumentException("Mora biti ime")
        }
        if(prezime.isBlank())
        {
            throw IllegalArgumentException("Mora biti prezime")
        }
        if(titula.isBlank())
        {
            throw IllegalArgumentException("Mora imati titulu")
        }
        if(godineIskustva <= 0)
        {
            throw IllegalArgumentException("Godine iskustva moraju biti vece od nule")
        }
        if(ekspertize.isEmpty())
        {
            throw IllegalArgumentException("Ekspertize ne mogu biti prazne")
        }
    }

    override fun identitet() : String
    {
        return "${ime} ${prezime}"
    }

    override fun profesionalnaTitula() : String
    {
        return "${titula}"
    }
    open fun osnovneInformacije() : String
    {
        return "Ime i prezime inženjera je ${ime} ${prezime}, njegova titula je ${titula}, godine iskustva ${godineIskustva} i njegove ekspertize su ${ekspertize}"
    }
}

class softverskiInzenjer(
    ime : String,
    prezime : String,
    titula : String,
    godineIskustva : Int,
    ekspertize :List<String>,
    var brojProjekata : Int
) : inzenjer(ime,prezime,titula,godineIskustva,ekspertize){

    override fun osnovneInformacije() : String
    {
        return "Ime i prezime inženjera je ${ime} ${prezime}, njegova titula je ${titula}, godine iskustva ${godineIskustva}, njegove ekspertize su ${ekspertize} i broj njegovih projekata je ${brojProjekata}            "
    }

    fun uspjesnost() : String
    {
        val tmp : String
        if(brojProjekata <= 0)
        {
            tmp ="${ime} nema projekata"
        }
        else if(brojProjekata >=5 && brojProjekata <=10)
        {
            tmp = "${ime} polako postaje sve uspjesniji"
        }
        else
        {
            tmp = "${ime} je veoma uspjesan"
        }
        return tmp

    }
}

class inzenjerElektrotehnike(
    ime : String,
    prezime : String,
    titula : String,
    godineIskustva : Int,
    ekspertize : List<String>,
    var brojCertifikata : Int
) : inzenjer(ime,prezime,titula,godineIskustva,ekspertize){

    override fun osnovneInformacije() : String
    {
        return "Ime i prezime inženjera je ${ime} ${prezime}, njegova titula je ${titula}, godine iskustva ${godineIskustva}, njegove ekspertize su ${ekspertize} i broj njegovih projekata je ${brojCertifikata}            "
    }

    fun uspjesnost() : String
    {
        val tmp : String
        if(brojCertifikata <= 0)
        {
            tmp ="${ime} nema projekata"
        }
        else if(brojCertifikata >=5 && brojCertifikata <=10)
        {
            tmp = "${ime} polako postaje sve uspjesniji"
        }
        else
        {
            tmp = "${ime} je veoma uspjesan"
        }
        return tmp

    }
}

fun grupisanjeEkspertize(inzenjer : List<inzenjer>) : MutableMap<String , MutableList<inzenjer>>
{
    val inzenjeriPrekoPet = mutableListOf<inzenjer>()
    for (inzenjer in inzenjer)
    {
        if(inzenjer.godineIskustva >5)
        {
            inzenjeriPrekoPet.add(inzenjer)
        }
    }

    val tmp =inzenjeriPrekoPet.fold(mutableMapOf<String, MutableList<inzenjer>>()) {
        akumulator, inzenjer ->
        for (i in inzenjer.ekspertize) {
            val lista = akumulator.getOrPut(i) { mutableListOf() }
            lista.add(inzenjer)
        }
        akumulator
    }
    return tmp
}

fun najiskusniji(inzenjer : List<inzenjer>) : MutableMap<String, inzenjer>
{
    val softver = inzenjer.filterIsInstance<softverskiInzenjer>()
    val elektro = inzenjer.filterIsInstance<inzenjerElektrotehnike>()
    val tmp = mutableMapOf<String, inzenjer>()

    if(softver.isNotEmpty())
    {
        var najiskusniji = softver.reduce { prvi , drugi -> if(prvi.godineIskustva >= drugi.godineIskustva) prvi else drugi }
        tmp["softverskiInzenjer"] = najiskusniji
    }
    if(elektro.isNotEmpty())
    {
        var najiskusniji = elektro.reduce { prvi , drugi -> if(prvi.godineIskustva >= drugi.godineIskustva) prvi else drugi }
        tmp["inzenjerElektortehnike"] = najiskusniji
    }

    return tmp
}

fun ukupnoProjekataICertifikata (inzenjer : List<inzenjer>) : Int
{
    var zbirProjekata = inzenjer.filterIsInstance<softverskiInzenjer>().sumOf { it.brojProjekata }
    var zbirCertifikata = inzenjer.filterIsInstance<inzenjerElektrotehnike>().sumOf { it.brojCertifikata}
    return zbirProjekata+zbirCertifikata
}



