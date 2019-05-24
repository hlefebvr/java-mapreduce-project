# java-mapreduce-project
MapReduce small project for "Virtualisation and Cloud computing" course at UNIGE (Genova)

## Project

This project takes a bunch of data and estimates the parameters for a Bayesian Classifier assuming a normal distribution law for the features.

## Makefile

Assuming that environment variable ``` HADOOP_CLASSPATH ``` is set and contains the paths to the hadoop classes and that ``` HADOOP_HOME ``` points to the installation folder of hadoop

- ``` make ``` : will compile and generate ``` .jar ```
- ``` make exec  ``` : will launch using hadoop
