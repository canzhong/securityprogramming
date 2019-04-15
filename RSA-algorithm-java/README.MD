This assignment is designed to implement the RSA using big integer package.

•It  is  required  to  use  Java  to  write  the  program. (You  may  chooseC/C++.)
•For Java, the packages java.math.BigInteger and java.security.SecureRandomshould be used.(For  C/C++,  you  may  use  GMP,  the  GNU  Multiple  Precision  Arith-metic Library, for handling big numbers.  GMP manual can be found athttp://www.swox.com/gmp/. Your program should be compiled usinggcc/g++.)
•Source code should be well commented.
•The prime numberspandqshould beat least1536bits.
•The  (absolute  value  of  the)  difference  of  two  primes  should  be  biggerthan 21000.
•A demonstration of encrypting a message (big number) and decryptingthe ciphertext (big number) should be given.  You should check that yourmessage is coprime to the modulusN=pq.
•Your program should also include the encryption and decryption timing.The encryption/decryption time should bebigger than zero.
•The public key componentemust be65537.
•Your program should be compilable using command linejavac.

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
