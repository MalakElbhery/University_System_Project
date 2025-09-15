public class RemoveEnrollment {
    int studentID ;
    RemoveEnrollment next ;
    RemoveEnrollment previous ;
    public  RemoveEnrollment(int studentID) {
        this.studentID = studentID;
        this.next = null;
    }
}
