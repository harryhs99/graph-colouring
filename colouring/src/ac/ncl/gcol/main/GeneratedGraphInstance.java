package ac.ncl.gcol.main;

public enum GeneratedGraphInstance {
    // p = 0.1
    rg50_0_1("rg50_0.1.col"),
    rg100_0_1("rg100_0.1.col"),
    rg250_0_1("rg250_0.1.col"),
    rg500_0_1 ("rg500_0.1.col"),
    rg750_0_1("rg750_0.1.col"),
    rg1000_0_1("rg1000_0.1.col"),
    rg1500_0_1("rg1500_0.1.col"),
    // p = 0.3
    rg50_0_3("rg50_0.3.col"),
    rg100_0_3("rg100_0.3.col"),
    rg250_0_3 ("rg250_0.3.col"),
    rg500_0_3("rg500_0.3.col"),
    rg750_0_3("rg750_0.3.col"),
    rg1000_0_3("rg1000_0.3.col"),
    rg1500_0_3("rg1500_0.3.col"),
    // p 0.5
    rg50_0_5("rg50_0.5.col"),
    rg100_0_5("rg100_0.5.col"),
    rg250_0_5("rg250_0.5.col"),
    rg500_0_5("rg500_0.5.col"),
    rg750_0_5("rg750_0.5.col"),
    rg1000_0_5("rg1000_0.5.col"),
    rg1500_0_5("rg1500_0.5.col"),
    // p = 0.7
    rg50_0_7("rg50_0.7.col"),
    rg100_0_7("rg100_0.7.col"),
    rg250_0_7("rg250_0.7.col"),
    rg500_0_7("rg500_0.7.col"),
    rg750_0_7("rg750_0.7.col"),
    rg1000_0_7("rg1000_0.7.col"),
    rg1500_0_7("rg1500_0.7.col"),
    // p = 0.9
    rg50_0_9("rg50_0.9.col"),
    rg100_0_9("rg100_0.9.col"),
    rg250_0_9("rg250_0.9.col"),
    rg500_0_9("rg500_0.9.col"),
    rg750_0_9 ("rg750_0.9.col"),
    rg1000_0_9("rg1000_0.9.col"),
    rg1500_0_9("rg1500_0.9.col");


    final String name;
    final String fileName;

    GeneratedGraphInstance(String name)
    {
        this.name = name;
        this.fileName = "colouring/src/ac/ncl/gcol/data/gengraphs/pregen/" + name;
    }
}
