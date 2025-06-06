A multi-threaded file server that supports the upload, download, delete, and rename file operations has been put into place. Used many folders to store files that were either posted to the server or transferred to the client.
Implementation:
The user is given an option; to upload, download, delete, rename, and terminate the software on the client side, the user is to input 1, 2, 3, 4, and 5, respectively.
To interact through RPC, the client and server ends of the communication chain require the Java RMI libraries.
Launch the Windows VSCode terminal to start the server as well as Client on a different instance.

UPLOAD
Enter option 1 from the aforesaid client side to upload. The file will be routed to or published to the server.

DOWNLOAD
Enter the option 2 at the client side. 
Once entered, the file will be sent from the serve to the client.

DELETE:
At the user end, enter option 3 to be erased.
File will be deleted from the server.

RENAME:
Select option 4 on the client side and enter the old and new file names. The aforementioned file would receive a new name that was supplied both at the server and client ends.

1 Project Part 2:
An RPC-emulating Java application that simulates a synchronized folder system, such as Dropbox on Microsoft Windows.
1.1  Implementation:
The user is prompted to input the client- and server-side folders that need to be synchronized.
On the client side, a timer thread is created every 10 seconds.
The set of file names stored every 10 seconds is stored in the Folder of the new files, and it checks to see whether a new file is added, removed, or updated.
If there is a modification or update to an existing file, the file is posted to the server. A new file is uploaded to the server if one is added. A file's deletion updates the server with the new information.
If a file name is changed at the client end, the new file is uploaded to the server after the old file has been deleted.

2 Project Part 3:
A Java program that commutes add and sort functionality synchronously and asynchronously via RPC.
2.1 Implementation:
To execute asynchronous add and sort, a straightforward Java call from the client to the server was used.
The user is then prompted to input option 1 or option 2 for adding or sorting depending on the synchronous/asynchronous choice they choose.
If addition is selected, the user is prompted to input the numbers to add, and the outcome is displayed on the prompt.
When doing synchronous computations, such as addition or sorting, the client must wait for the server to return the results.
In the meanwhile, for asynchronous computation, the client doesn't wait for the server to send results; instead, the server returns none at the conclusion, and the client then requests the results.

Technologies Used:
Using Java, the application was designed and created to meet the stated need.
Developed the aforementioned program on a Windows setting. Running this program would be sufficient on any Windows PC.
Applied the idea of multithreading.

Things Learnt:
Has solid grasp of the client-server architecture.
Acquired knowledge of the fundamentals, applications, and usage of RPC.
Practical experience with Java programming and multi-threading.


CHALLENEGES FACED: 

🧠 Conceptual & Design Challenges
Understanding RPC and RMI:
Getting familiar with Java RMI libraries and the client-server RPC model can be tricky, especially managing remote method calls and handling return values.

Synchronous vs Asynchronous RPC:
Designing both sync and async behavior involves extra logic, especially for managing callbacks, acknowledgments, or deferred responses on the client side.

Multithreading Complexity:
Implementing a multi-threaded file server and ensuring thread safety (especially during simultaneous uploads/downloads) is difficult without race conditions or file locks.

💻 Implementation Challenges
File Handling in Java:
Reading, writing, renaming, and deleting files across different folders while maintaining consistency is error-prone and needs careful exception handling.

Folder Synchronization:
Simulating Dropbox-like syncing using helper threads every 10 seconds requires efficient file change detection and comparison (e.g., using timestamps or hashing).

File Rename Propagation:
Ensuring that a renamed file on the client reflects correctly on the server without duplication or data loss can be challenging.

⚙️ Environment & Tooling Issues
VS Code Setup with Java RMI:
Getting RMI to work correctly often involves setting up policy files, registering stubs, and ensuring the classpath and security manager are properly configured.

Windows-Specific Path Handling:
File path compatibility and directory management across Windows machines can introduce bugs if absolute vs relative paths aren't managed carefully.

Networking & Port Conflicts:
Running multiple client/server instances can cause port binding errors or connectivity issues if sockets aren’t handled cleanly after shutdown.

🔄 Testing & Debugging Difficulties
Debugging Async RPCs:
Since async methods don’t immediately return results, tracking down why the results aren’t synced or received can be harder.

Tracking File Sync Changes:
Verifying if a file was updated, renamed, or deleted within the 10-second polling interval might lead to missed or duplicate operations.

Testing Multi-client Interactions:
Running multiple clients accessing the same server (for file ops or computation) might result in concurrency issues if locks aren't handled correctly.

📊 Other Challenges
Accurate Division of Work:
Clearly separating responsibilities across team members (e.g., one on sync, one on async) and integrating them into one cohesive system may have required extra coordination.

Documentation & Report Writing:
Writing an understandable and technically correct README/report for a 3-part project involving RPC, file sync, and computation could be time-consuming.



