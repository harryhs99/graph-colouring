package ac.ncl.gcol.main;

public enum RandomGraphInstance {
    // KNOWN CHROMATIC NUMBER
    // Leighton graphs
    LE450_5A("le450_5a.col", 5),
    LE450_5B("le450_5b.col", 5),
    LE450_5C("le450_5c.col", 5),
    LE450_5D("le450_5d.col", 5),
    LE450_15A("le450_15a.col", 15),
    LE450_15B("le450_15b.col", 15),
    LE450_15C("le450_15c.col", 15),
    LE450_15D("le450_15d.col", 15),
    LE450_25A("le450_25a.col", 25),
    LE450_25B("le450_25b.col", 25),
    LE450_25C("le450_25c.col", 25),
    LE450_25D("le450_25d.col", 25),

    // UNKNOWN CHROMATIC NUMBER
    // Johnson graphs
    DSJC125_1("DSJC125.1.col", 0.1F),
    DSJC250_1("DSJC250.1.col", 0.1F),
    DSJC500_1("DSJC500.1.col", 0.1F),
    DSJC1000_1("DSJC1000.1.col", 0.1F),
    DSJC125_5("DSJC125.5.col", 0.5F),
    DSJC250_5("DSJC250.5.col", 0.5F),
    DSJC500_5("DSJC500.5.col", 0.5F),
    DSJC1000_5("DSJC1000.5.col", 0.5F),
    DSJC125_9("DSJC125.9.col", 0.9F),
    DSJC250_9("DSJC250.9.col", 0.9F),
    DSJC500_9("DSJC500.9.col", 0.9F),
    DSJC1000_9("DSJC1000.9.col", 0.9F);



    final int chromaticNumber;
    final float pValue;
    final String name;
    final String fileName;

    RandomGraphInstance(String name, float pValue)
    {
        this.name = name;
        this.fileName = "colouring/src/ac/ncl/gcol/data/testgraphs/random/unknownX/" + name;
        this.pValue = pValue;
        this.chromaticNumber = -1;
    }

    RandomGraphInstance(String name, int chromaticNumber)
    {
        this.name = name;
        this.fileName = "colouring/src/ac/ncl/gcol/data/testgraphs/random/knownX/" + name;
        this.pValue = 0;
        this.chromaticNumber = chromaticNumber;
    }
}
