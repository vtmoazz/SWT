import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger LOG = Logger.getLogger(Square.class.getName());

    @Override
    public void draw() {
        LOG.info("Drawing square");
    }

    @Override
    public void resize() {
        LOG.info("Resizing square");
    }
}
