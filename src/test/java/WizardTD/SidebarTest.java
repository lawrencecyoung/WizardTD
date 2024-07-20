// import org.junit.jupiter.api.Test;
// import WizardTD.Monster;
// import WizardTD.Tower;
// import WizardTD.MonsterConfig;
// import WizardTD.App;
// import WizardTD.PathFinding;
// import WizardTD.Button;
// import WizardTD.ButtonFastForward;
// import WizardTD.ButtonPause;
// import WizardTD.ButtonBuildTower;
// import WizardTD.ButtonUpgrade;
// import WizardTD.ButtonManaPool;
// import WizardTD.ButtonSpell;
// import WizardTD.Sidebar;



// import processing.core.PApplet;
// import processing.core.PImage;
// import processing.data.JSONObject;

// import java.io.*;
// import java.util.*;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// public class SidebarTest {
//     App app = new App();
//     JSONObject json = app.getJson();
//     Sidebar sidebar = new Sidebar(app, json);

//     int towerCost = json.getInt("tower_cost");
//     int manaPoolInitial = json.getInt("mana_pool_spell_initial_cost");
//     int manaPoolIncreasePerUse = json.getInt("mana_pool_spell_cost_increase_per_use");
//     float manaPoolCapMultiplier = json.getFloat("mana_pool_spell_cap_multiplier");
//     float manaPoolGainedMultiplier = json.getFloat("mana_pool_spell_mana_gained_multiplier");

//     ButtonFastForward buttonFastForward = new ButtonFastForward(app, 650, 50, 40, 40, "2x speed", "FF");
//     ButtonPause buttonPause = new ButtonPause(app, 650, 100, 40, 40, "PAUSE", "P");
//     ButtonBuildTower buttonBuildTower = new ButtonBuildTower(app, 650, 150, 40, 40, "Build&tower", "T", towerCost);
//     ButtonUpgrade buttonUpgradeRange = new ButtonUpgrade(app, 650, 200, 40, 40, "Upgrade&Range", "U1");
//     ButtonUpgrade buttonUpgradeSpeed = new ButtonUpgrade(app, 650, 250, 40, 40, "Upgrade&Speed", "U2");
//     ButtonUpgrade buttonUpgradeDamage = new ButtonUpgrade(app, 650, 300, 40, 40, "Upgrade&Damage", "U3");
//     ButtonManaPool buttonManaPool = new ButtonManaPool(app, 650, 350, 40, 40, "Mana pool", "M", manaPoolInitial, manaPoolIncreasePerUse, manaPoolCapMultiplier, manaPoolGainedMultiplier);
//     ButtonSpell buttonSpell = new ButtonSpell(app, 650, 400, 40, 40, "Spell", "S");



    // @Test
    // public void testGetButtonFastForward() {        
    //     assertEquals(buttonFastForward, buttonFastForward);
    // }
// }
