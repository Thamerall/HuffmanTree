# HuffmanTree

# Project: Huffman Coding
Huffman coding is a widely used and very effective technique for compressing data; savings of 20% to 90% are typical, depending on the characteristics of the data being compressed. Huffman code is a variable length code whose length depends on the frequencies of characters in a message. It is constructed by building a Huffman Tree based on the frequencies of characters.
A binary bit code for each character is determined from the Huffman tree and used to encode a message. The Huffman tree is also used to decode an encoded message as it provides a way to determine which bit sequences translate back to a character.
Write a program which (i) compresses the data of the given message using Huffman code, and then (ii) decompresses a compressed file in order to retrieve the original message.
In this project, your program has to do following tasks:
A. Construction of a Frequency Table of characters/symbols B. Construction of Encoding Tree, i.e. Huffman tree.
C. Encoding of a message to binary code.
D. Decoding of the encoded message.
E. Analysis
A. Construction of Frequency Table of characters/symbols:
(1) Create an input file 'input' with the poem ‘Desiderata ’ written by Max Ehrmann in 1920’s .
(2). Parse the text of input file, count the frequency of each character/symbol, generate the table of frequencies, and print this Frequency_Table into the output file named ‘output’.
NOTE: Every character is case-sensitive, and a space character(‘ ‘) and carriage-return character (i.e. linefeed, enter) should be also distinguished.
B. Construction of Huffman Tree
(1) Using the Frequency_Table and a priority queue implemented by a minimum heap, construct your Huffman Tree.
(2) Store the binary code-word of each character generated from the above Huffman tree in the ‘Huffman_Table’, and print this Huffman_Table in the same output file ‘output’.
(3) Draw the above Huffman Tree manually or using the graphic software. Insert (or save) its image in the file ‘huffman-image’.
C. Encoding of the message
(1) Encode the given message in the input file ‘input’ and store the encoded message in the output file ‘encoded’.
(2) What is the length of the encoded message? i.e. the number of bits (0s & 1s) of encoded message.
(3) Your program should print all the results in the output file ‘output’.
a) Frequency_Table,
b) Huffman_Table,
c) the length of the above encoded message,
On the other hand, your 2nd output file ‘encoded’ includes the entire encoded message of binary strings.
D. Decoding:
Suppose that you’ve received the compressed message file ‘encoded’.
Now, you have to decode the binary message in ‘encoded’, restoring the original text message.
1. Read the encoded message from a file ‘encoded’ ,
2. Parse it and decode the message, using your Huffman_Table.
Your decoded message will be put in the 3rd output file ‘decoded’.
If the decoded message in 'decoded' is equal to the original message in 'input',
both of your encoding and decoding of the message are successful.
E. Printing the Result and Analysis (1) Printing in output file:
Print all the results in the output file ‘output’, including
a) Frequency_Table,
b) Huffman_Table,
c) the length of the encoded message by your Huffman code, and
o The Huffman_Table is printed in the following format in output file. character = its encoded codeword
e.g.) a=010011, i.e. the encoded codeword of ‘a’ is 010011.
o The 1st line of the file will contain the carriage-return character, followed by ‘=’ and its code word.
Since the carriage-return character forces a line-feed when it’s viewed,
the 1st line of ‘output’ file is blank, and the 2nd line starts with a ‘=’, followed by its codeword of the carriage-return character.
Thus, it’ll look like: e.g.) blank line
= 1011101: the encoding of ‘carriage-return’ is 1011101.
o Every character in the message, including space, comma(,), period(.), semicolon(;) will be encoded.
o The upper case letters should be distinguished from their lower case letters in their encodings; 'G', 'A', 'S', 'I', 'E', 'K', 'B', ‘N’, ‘T’, ‘M’, ‘Y’, ‘W’.
o Before printing each table, give a caption of the table to print: e.g.) Frequency_Table, or Huffman_Table.
(2) Comparison of the size of original file in ASCII code and the encoded file:
Suppose that the input message is encoded in ASCII code which is a 7 bit fixed binary code: http://en.wikipedia.org/wiki/ASCII
What is the length of ASCII encoding of the message in input?
Compare the length of ASCII encoding of input file with the length of your Huffman encoding.
d) Write this comparison result as well as the above a) – c) in ‘output’ file.
