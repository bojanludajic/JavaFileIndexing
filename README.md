# File Indexing Application

Solution for a JetBrains Internship 2025 task (GoLand onboarding experience).<br>
This application allows you to index `.txt` files and directories, and then query the indexed files for specific words.<br>
**Note**: All filles will be processed to extract a list of words based on the following regex: `"\\W+"`. This regex effectively splits text into words while ignoring punctuation marks, special characters and whitespace. Words in this context are defined as a sequence of letters and digits, so any characters that are not part of this set will be used as delimiters.

## Features

- **Indexing**: Index individual `.txt` files or entire directories.
- **Querying**: Search for specific words in the indexed files. Search results are case insensitive.

## Usage

### Indexing a File or Directory

To index a file or directory, use the `index` command followed by the absolute path of the file or directory.

```java
Insert command (index, query, exit)
index
Enter absolute path of file/directory to index: 
/path/to/your/file_or_directory
File successfully indexed.
```

### Querying indexed files

To search for a specific word in the indexed files, use the `query` command followed by the word you want to search for.

```java
Insert command (index, query, exit)
query
Enter word for search:
Kotlin
Files containing the word "Kotlin":
/path/to/your/file.txt
```

#### No files found
```java
Insert command (index, query, exit)
query
Enter word for search:
Java
No files containing the specified word were found.
```

### Exiting the application

```java
Inser command (index, query, exit)
exit
Exiting application.
```






