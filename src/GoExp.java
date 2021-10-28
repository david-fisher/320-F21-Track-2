public class GoExp extends OpTree {
    public GameObject value;
    public GoExp(GameObject value) {
        Super("GameObject");
        this.value = value;
    }
}