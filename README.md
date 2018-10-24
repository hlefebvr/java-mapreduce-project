# java-mapreduce-project
MapReduce small project for "Virtualisation and Cloud computing" course at UNIGE (Genova)

## For now...

For now this repo only contains the basic hadoop example "count word" to test configuration...

## Makefile

Assuming that environment variable ``` HADOOP_CLASSPATH ``` is set and contains the paths to the hadoop classes and that ``` HADOOP_HOME ``` points to the installation folder of hadoop

- ``` make ``` : will compile and generate ``` .jar ```
- ``` make exec  ``` : will launch using hadoop