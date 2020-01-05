package tetris.configuration;

public class TetrisProperties extends AbstractProperty {

    private static TetrisProperties instance;

    public static TetrisProperties getInstance() {

        if (instance !=  null) {

            return instance;
        }

        synchronized (TetrisProperties.class) {

            instance = new TetrisProperties();

            return instance;
        }
    }

    public enum Keys implements PropertyName {

        //==================================================================================================================
        // 1. Map
        //------------------------------------------------------------------------------------------------------------------
        map_single_heightSize("map.single.height-size", "20"),
        map_single_widthSize("map.single.width-size", "10"),

        ;

        private String key;
        private String defaultValue;

        Keys(String key, String defaultValue) {

            this.key = key;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return this.key;
        }

        @Override
        public String defaultValue() {
            return this.defaultValue;
        }
    }
}
