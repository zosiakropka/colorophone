Colorophone
===========

Simple app to simulate colorophone - DSP system in which color reacts on sound captured.

Compile and run
---------------

Go to the main project directory.

	~$ cd colorphone

*To compile*:

	colorphone$ mkdir build;
	colorphone$ javac -d build/ colorophone/*java;
	colorphone/build$ cd build;
	colorphone/build$ jar -cvfm Colorophone.jar ../manifest.mf colorophone/*.class
	colorphone/build$ chmod +x Colorophone.jar 

*To run*:

	colorphone/build$ ./Colorophone.jar 

Test it
-------

Try talking quite and louder to see colors changing.