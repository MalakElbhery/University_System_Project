public class courseNode {
    int courseID;
    courseNode next;
    EnrolledStudentNode enrolledStudentsHead;
    public courseNode(int id) {
        courseID = id;
        next = null;
        enrolledStudentsHead = null;
    }
}
