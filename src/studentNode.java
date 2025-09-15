public class studentNode {
    int studentID;
    studentNode next;
    courseNode enrolledCoursesHead;
    public studentNode(int id) {
        studentID = id;
        next = null;
        enrolledCoursesHead = null;
    }
}
