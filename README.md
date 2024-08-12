# Graph Colouring 
This repo provides a small application that implements some constructive graph colouring algorithms. This was for my final project and dissertation for the award of MSc in Computer Science at Newcastle University in August 2024. 

## Abstract
The Graph Colouring Problem (GCP) is one of the best known problems in the field of Graph Theory, with significant applications in scheduling, timetabling, and register alloca- tion to name a few. This project investigates the efficiency and efficacy of several constructive algorithms designed to provide upper bound solutions to the GCP. The algorithms include: GREEDY, WELSH-POWELL, DSATUR and RLF (Recurscive Largest First). The GREEDY algorithm was implemented using two variations based on vertex ordering heuristics.

This study evaluates the performance of each of these algorithms through extensive test- ing on multiple different graph instances, including randomly generated G(n, p) graphs and standard benchmarks such DIMACS test instances with known χ(G). The results find that no single algorithm outperforms all the others in all contexts. RLF proves to be most efficacious at colouring large and dense graphs, however it is by far the least efficient. DSATUR excels in colouring small and sparse graphs, however again it is the second least efficient. GREEDY and WELSH-POWELL prove to be the most efficient algorithms however they are also the least and second least efficacious respectively.

This research highlights the trade-offs between algorithmic efficiency and the quality of solu- tion generated. This project also provides a computer program that users can interact with to test the included algorithms on test instances.

## File Structure
More information about the process and results can be found in GraphColouringDiss.pdf and all the LATEX src code can be found in the DissSource. The program along with all the algorithms and classes for storing the graphs can be found in the GraphColouring folder. 
