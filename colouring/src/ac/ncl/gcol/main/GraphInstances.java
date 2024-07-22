package ac.ncl.gcol.main;

public enum GraphInstances {
    // KNOWN CHROMATIC NUMBER
    // Knuth graphs
    ANNA("anna.col", 11),
    DAVID("david.col", 11),
    HOMER("homer.col", 13),
    HUCK("huck.col", 11),
    JEAN("jean.col", 10),
    GAMES120("games120.col", 9),
    MILES250("miles250.col", 8),
    MILES500("miles500.col", 20),
    MILES750("miles750.col", 31),
    MILES1000("miles1000.col", 42),
    MILES1500("miles1500.col", 73),
    // Culberson graphs
    FLAT300_26_0("flat300_26_0.col", 26),
    FLAT300_28_0("flat300_28_0.col", 28),
    FLAT1000_50_0("flat1000_50_0.col", 50),
    FLAT1000_60_0("flat1000_60_0.col", 60),
    FLAT1000_76_0("flat1000_76_0.col", 76),
    // Lewandowski graphs
    FPSOL2_I_1("fpsol2.i.1.col", 65),
    FPSOL2_I_2("fpsol2.i.2.col", 30),
    FPSOL2_I_3("fpsol2.i.3.col", 30),
    INITHX_I_1("inithx.i.1.col", 54),
    INITHX_I_2("inithx.i.2.col", 31),
    INITHX_I_3("inithx.i.3.col", 31),
    MULSOL_I_1("mulsol.i.1.col", 49),
    MULSOL_I_2("mulsol.i.2.col", 31),
    MULSOL_I_3("mulsol.i.3.col", 31),
    MULSOL_I_4("mulsol.i.4.col", 31),
    MULSOL_I_5("mulsol.i.5.col", 31),
    ZEROIN_I_1("zeroin.i.1.col", 49),
    ZEROIN_I_2("zeroin.i.2.col", 30),
    ZEROIN_I_3("zeroin.i.3.col", 30),
    // Trick Mycielski graphs
    MYCIEL3("myciel3.col", 4),
    MYCIEL4("myciel4.col", 5),
    MYCIEL5("myciel5.col", 6),
    MYCIEL6("myciel6.col", 7),
    MYCIEL7("myciel7.col", 8),

    // UNKNOWN CHROMATIC NUMBER
    //Lewandowski graphs
    SCHOOL1("school1.col"),
    SCHOOL1_NSH("school1.col"),
    //Latin Square
    LATIN_SQUARE_10("latin_square_10.col");

    final int chromaticNumber;
    final String name;
    final String fileName;

    GraphInstances(String name)
    {
        this.name = name;
        this.fileName = "colouring/src/ac/ncl/gcol/data/testgraphs/instances/unknownX/" + name;
        this.chromaticNumber = -1;
    }

    GraphInstances(String name, int chromaticNumber)
    {
        this.name = name;
        this.fileName = "colouring/src/ac/ncl/gcol/data/testgraphs/instances/knownX/" + name;
        this.chromaticNumber = chromaticNumber;
    }
}
