 REQUIREMENTS
 
 Grades a student answer to a scrambled set of text fragments.
 The problem is given as a set sentences, known to the grading system by number, such as 
 3 4 7 11 2 5 1. The answer is of the form 1 2 3 4 (i.e., does not necessarily include 
 all fragments).
 
 The evaluation of an answer is computed in a positive manner only: answers receive credit 
 based on what's good (there is no subtraction of points).
 The system uses rubric sets to evaluate an answer. The "order" rubrics are associated
 with the class OrderRubrics--where the specifications of an associated file are specified.
 The other rubric set gives credit for consecutive pairs in the answer.
 
 Input (student answers) are screened for maximum and minimum (specified in the class 
 StudentPaper); only numbers between these are accepted. The latter are reported to console
 without breaks. That's the meaning of "an example...modify in StudentPaper; not separated."
 
 ------------- TYPICAL I/O: -------------
 
enter 'N' if no more students--otherwise, student's name: Alan Aden
You input: Alan Aden
Please enter student answer as in '1 2 3 6 8 4': 4 5 88 -9
Student answer is--->4 5 88 -9
Entries within bounds (an example...modify in StudentPaper; not separated): 
45
Enter N if no more students--otherwise, student's name: Beryl Bain
You input: Beryl Bain
Please enter student answer as in '1 2 3 6 8 4': 1 2 3 6 8 99
Student answer is--->1 2 3 6 8 99
Entries within bounds (an example...modify in StudentPaper; not separated): 
12368
Enter N if no more students--otherwise, student's name: N
You input: N
EOF reached in student-ungraded-repository.dat
Grading complete; records stored as StudentPaper objects in student-graded-repository.dat
======THE TOTAL NUMBER OF POINTS IS======>37.0

==>Alan Aden: Answer ...[4, 5, 88, -9] Grade ...0.0
Feedback:

==>Beryl Bain: Answer ...[1, 2, 3, 6, 8, 99] Grade ...14.0
Feedback:
 You are correct that #1 and #2 are consecutive.
 You are correct that #1 occurs before #2.
EOF reached in student-ungraded-repository.dat

==========ALL STUDENTS REPORTED===========
 
 
 ------------- TO EXECUTE -------------
 
 MainKGrade4.main()
