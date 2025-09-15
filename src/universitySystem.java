import java.util.Stack;
public class universitySystem {
    studentNode studentsHead;
    courseNode coursesHead;

    public universitySystem() {
        studentsHead = null;
        coursesHead = null;
    }

    public void addStudent(int studentID) {
        // Check if student already exists
        if (findStudent(studentID) != null) {
            System.out.println("Student with ID " + studentID + " already exists.");
            return;
        }
        // Adds student node
        studentNode newStudent = new studentNode(studentID);
        newStudent.next = studentsHead;
        studentsHead = newStudent;

        System.out.println("Student with ID " + studentID + " added successfully.");
    }

    // Helper method to check if student already exists again
    // why we use helper method?? for more clean code instead of repeating codes and algorithms
    public studentNode findStudent(int studentID) {
        studentNode current = studentsHead;
        while (current != null) {
            if (current.studentID == studentID)
                return current;
            current = current.next;
        }
        return null;
    }
    public void addCourse(int courseID) {
        // Check if course already exists
        if (findCourse(courseID) != null) {
            System.out.println("Course with ID " + courseID + " already exists.");
            return;
        }
        // Adds course node
        courseNode newCourse = new courseNode(courseID);
        newCourse.next = coursesHead;
        coursesHead = newCourse;

        System.out.println("Course with ID " + courseID + " added successfully.");
    }

    // Helper method to find a course by ID
    // For the same reason
    public courseNode findCourse(int courseID) {
        courseNode current = coursesHead;
        while (current != null) {
            if (current.courseID == courseID)
                return current;
            current = current.next;
        }
        return null;
    }
    public void removeStudent(int studentID) {
        if (studentsHead == null) {
            System.out.println("No students to remove.");
            return;
        }

        // If the student to remove is the head
        if (studentsHead.studentID == studentID) {
            studentsHead = studentsHead.next;
            System.out.println("Student with ID " + studentID + " removed.");
            return;
        }

        // searches for the student
        studentNode prev = studentsHead;
        studentNode current = studentsHead.next;

        while (current != null) {
            if (current.studentID == studentID) {
                prev.next = current.next;
                System.out.println("Student with ID " + studentID + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("Student with ID " + studentID + " not found.");
    }
    public void removeCourse(int courseID) {
        if (coursesHead == null) {
            System.out.println("No courses to remove.");
            return;
        }

        // If the course to remove is the head
        if (coursesHead.courseID == courseID) {
            coursesHead = coursesHead.next;
            System.out.println("Course with ID " + courseID + " removed.");
            return;
        }

        // searches for the course
        courseNode prev = coursesHead;
        courseNode current = coursesHead.next;

        while (current != null) {
            if (current.courseID == courseID) {
                prev.next = current.next;
                System.out.println("Course with ID " + courseID + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("Course with ID " + courseID + " not found.");
    }
    // Bonus gad3ana mnna
    // Print all students and check if there are students exists
    public void printAllStudents() {
        if (studentsHead == null) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Students:");
        studentNode current = studentsHead;
        while (current != null) {
            System.out.println("- ID: " + current.studentID);
            current = current.next;
        }
    }

    //  Bonus bardo gad3na mnna
    // Print all courses and check if there are courses exists
    public void printAllCourses() {
        if (coursesHead == null) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Courses:");
        courseNode current = coursesHead;
        while (current != null) {
            System.out.println("- ID: " + current.courseID);
            current = current.next;
        }
    }
    Stack<EnrollmentAction>undoStack=new Stack<>();
    Stack<EnrollmentAction>redoStack=new Stack<>();

    public void enrollstudentincourse(int studentID, int courseID ) {
        studentNode student = findStudent(studentID);
        courseNode course = findCourse(courseID);
        if (student == null) {
            System.out.println("Student with ID " + studentID + " not found.");
        }
        if (course == null) {
            System.out.println("Course with ID " + courseID + " not found.");
        }
        EnrolledStudentNode current = course.enrolledStudentsHead;
        while (current != null) {
            if (current.studentID == studentID) {
                System.out.println("Student with ID " + studentID + " already enrolled.");
                return;
            }
            current = current.next;
        }undoStack.push(new EnrollmentAction("enroll",studentID,courseID));
        redoStack.clear();


    }


    public void removestudentfromcourse(int courseID, int studentID){
        courseNode course = findCourse (courseID);
        studentNode student = findStudent (studentID);
        EnrolledStudentNode current=course.enrolledStudentsHead;
        EnrolledStudentNode previous=null;
        while(current!=null) {
            if (current.studentID == studentID) {
                if(previous==null) {
                    course.enrolledStudentsHead=current.next;
                }
                else{
                    previous.next=current.next;
                }
                System.out.println("student with ID "+studentID+"removed from course");
                return;
            }
            previous=current;
            current = current.next;
        }
        System.out.println("Student with ID "+studentID+" not found.");
        EnrolledStudentNode newenrollment = new EnrolledStudentNode(studentID);
        newenrollment.next = course.enrolledStudentsHead;
        course.enrolledStudentsHead = newenrollment;
        undoStack.push(new EnrollmentAction("remove",studentID,courseID));
        redoStack.clear();
    }

    public void PrintStudentsinCourse( int courseID){
        courseNode course= findCourse(courseID);
        if(course==null) {
            System.out.println("Course with ID "+courseID+" not found.");
            return;
        }
        EnrolledStudentNode current= course.enrolledStudentsHead;
        if(current==null) {
            System.out.println("no student found in "+courseID+".");
            return;
        }
        System.out.println("Students enrolled in course "+courseID);
        while(current!=null) {
            studentNode student= findStudent (current.studentID);
            if(student!=null) {
                System.out.println("StudentID: "+student.studentID);
            }
            current=current.next;
        }

    }
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        EnrollmentAction lastaction = undoStack.pop();
        if(lastaction.actionType.equals("enroll")){
            removestudentfromcourse(lastaction.studentID, lastaction.courseID);
            redoStack.push(new EnrollmentAction("enroll",lastaction.studentID,lastaction.courseID));
        }else if(lastaction.actionType.equals("remove")){
            enrollstudentincourse(lastaction.studentID, lastaction.courseID);
            redoStack.push(new EnrollmentAction("remove",lastaction.studentID,lastaction.courseID));
        }
    }public void redo(){
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }EnrollmentAction lastUndone = redoStack.pop();

        if(lastUndone.actionType.equals("enroll")){
            enrollstudentincourse(lastUndone.studentID, lastUndone.courseID);

            undoStack.push(new EnrollmentAction("enroll",lastUndone.studentID,lastUndone.courseID));
        }else if(lastUndone.actionType.equals("remove")){
            removestudentfromcourse(lastUndone.studentID, lastUndone.courseID);
            undoStack.push(new EnrollmentAction("remove",lastUndone.studentID,lastUndone.courseID));
        }
    }
    public void printCoursesOfStudent(int studentID) {
        studentNode student = findStudent(studentID);
        if (student == null) {
            System.out.println("Student with ID " + studentID + " not found.");
            return;
        }

        boolean found = false;
        courseNode currentCourse = coursesHead;

        System.out.println("Courses for student with ID " + studentID + ":");
        while (currentCourse != null) {
            EnrolledStudentNode enrolled = currentCourse.enrolledStudentsHead;
            while (enrolled != null) {
                if (enrolled.studentID == studentID) {
                    System.out.println("- Course ID: " + currentCourse.courseID);
                    found = true;
                }
                enrolled = enrolled.next;
            }
            currentCourse = currentCourse.next;
        }

        if (!found) {
            System.out.println("Student is not enrolled in any courses.");
        }
    }
}
