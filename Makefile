MAIN=WordCount
SRC=src
BUILD=build
EXEC_PARAMETERS=input/input.txt output/
# HADOOP=/usr/local/hadoop-2.8.5/bin/hadoop

CLASSES=$(SRC)/*.java

%.class: %.java
	javac -classpath ${HADOOP_CLASSPATH} -d ./$(BUILD) $*.java

default: classes
	(cd $(BUILD) && jar -cvf $(MAIN).jar .)

classes: $(CLASSES:.java=.class)

exec:
	rm -rf output
	${HADOOP_HOME}/bin/hadoop jar $(BUILD)/$(MAIN).jar $(MAIN) $(EXEC_PARAMETERS)
