#set java environment

#JAVA_HOME=/usr/local/javaenv/jdk1.6.0_37

M2_HOME=/home/andy/dev/maven

#export CLASSPATH=$JAVA_HOME/lib:$CLASSPATH

#export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH

export JAVA_HOME=/usr/local/dev/jdk
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$M2_HOME/bin:$PATH
