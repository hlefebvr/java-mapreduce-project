compile:
	(cd src && javac -verbose -classpath ${HADOOP_CLASSPATH} -d ../build WordCount.java)
	(cd build && jar -cvf WordCount.jar .)

run:
	rm -rf build/output
	(cd build && /usr/local/hadoop-2.8.5/bin/hadoop jar WordCount.jar WordCount ../input output)

clean:
	rm src/*.class src/*.jar
