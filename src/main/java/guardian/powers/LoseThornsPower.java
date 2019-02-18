package guardian.powers;


import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class LoseThornsPower extends AbstractGuardianPower {
    public static final String POWER_ID = "Guardian:LoseThornsPower";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;

    public static String[] DESCRIPTIONS;

    public LoseThornsPower(AbstractCreature owner, int amount) {

        this.ID = POWER_ID;
        this.owner = owner;

        this.setImage("ThornsDown84.png", "ThornsDown32.png");

        this.type = POWER_TYPE;

        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }


    public void updateDescription() {


        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];


    }


    public void atStartOfTurn() {

        flash();

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, LoseThornsPower.POWER_ID));

        if (this.owner.hasPower("Thorns")){
            if (this.owner.getPower("Thorns").amount <= this.amount) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Thorns"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Thorns", this.amount));
            }
        }
    }

}


