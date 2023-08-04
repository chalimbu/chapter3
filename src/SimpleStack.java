public class SimpleStack<A> {

    public static void main(String args[]) {
        SimpleStack<Integer> se = new SimpleStack<Integer>();
        se.push(1);
        se.push(2);
        se.push(3);
        System.out.println("" + se.peak());
        System.out.println("" + se.pop());
        System.out.println("" + se.pop());
        System.out.println("" + se.pop());
        System.out.println("" + se.pop());

    }

    private static class StackElement<A> {
        A data;
        StackElement<A> next;

        public StackElement(A data, StackElement<A> next) {
            this.data = data;
            this.next = next;
        }
    }

    StackElement<A> h = null;

    public void push(A data) {
        StackElement ne = new StackElement<A>(data, h);
        h = ne;
    }

    public A pop() {
        StackElement<A> le = h;
        if (le == null) {
            throw new RuntimeException("stack is empty");
        }
        h = h.next;
        return le.data;
    }

    public boolean isEmpty() {
        if (h == null) {
            return true;
        }
        return false;
    }

    public A peak() {
        if (h == null) {
            throw new RuntimeException("stack is empty");
        }
        return h.data;
    }

}