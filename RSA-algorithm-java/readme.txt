Create a new directory for my homework assignment or run from the zip file.
From Terminal, navigate to the directory that contains the RSA.java file
run : javac RSA.java
run : java RSA

How to run :
After running the RSA program. It will request you to input number of bit length you desire the prime numbers to be.

Type any number and click enter.
  If number is invalid (ie it is less than 1536) it will reject it and continue to ask you for a correct input
  unless you quit by CTRL+C
  Else it will create the prime numbers, keys, and everything.
  This implementation does not check the validity of the input pertaining to integers, so it assumes the user will
  input a number and not a string.

It then will ask you to go through the encryption, decryption phases until you are satisfied.
It will not stop unless you input the special stop character '~', which will then cause it to stop and exit.
This implementation does not check whether or not the first time you input is the termination character '~'. It will
instead continue to encrypt and decrypt the termination character as input.
