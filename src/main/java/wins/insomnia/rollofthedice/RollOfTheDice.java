package wins.insomnia.rollofthedice;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;
import wins.insomnia.rollofthedice.proxy.ClientProxy;
import wins.insomnia.rollofthedice.proxy.IModProxy;
import wins.insomnia.rollofthedice.proxy.ServerProxy;

@Mod(modid = RollOfTheDice.MODID, name = RollOfTheDice.NAME, version = RollOfTheDice.VERSION)
public class RollOfTheDice {
    public static final String MODID = "rollofthedice";
    public static final String NAME = "Roll of the Dice";
    public static final String VERSION = "1.0";
    protected static RollOfTheDice instance;
    private IModProxy proxy;

    public RollOfTheDice() {
        instance = this;
    }

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();

        if (event.getSide() == Side.CLIENT) {
            clientPreInit();
        } else {
            serverPreInit();
        }


    }

    public IModProxy getProxy() {
        return proxy;
    }

    private void clientPreInit() {
        proxy = new ClientProxy();
    }

    private void serverPreInit() {
        proxy = new ServerProxy();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register stuff
    }

    public static RollOfTheDice instance() {
        return instance;
    }

    public static boolean isClient() {
        return instance().proxy instanceof ClientProxy;
    }

    public Logger getLogger() {
        return logger;
    }
}
