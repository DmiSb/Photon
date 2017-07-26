package ru.dmisb.photon.core;

import android.databinding.Bindable;

public abstract class BaseFilterViewModel extends BaseViewModel {
    @Bindable
    public abstract boolean isMeat();

    public abstract void setMeat(boolean meat);

    @Bindable
    public abstract boolean isFish();

    public abstract void setFish(boolean fish);

    @Bindable
    public abstract boolean isVegetables();

    public abstract void setVegetables(boolean vegetables);

    @Bindable
    public abstract boolean isFruit();

    public abstract void setFruit(boolean fruit);

    @Bindable
    public abstract boolean isCheese();

    public abstract void setCheese(boolean cheese);

    @Bindable
    public abstract boolean isDesert();

    public abstract void setDesert(boolean desert);

    @Bindable
    public abstract boolean isDrinks();

    public abstract void setDrinks(boolean drinks);

    @Bindable
    public abstract boolean isNuancesRed();

    public abstract void setNuancesRed(boolean nuancesRed);

    @Bindable
    public abstract boolean isNuancesOrange();

    public abstract void setNuancesOrange(boolean nuancesOrange);

    @Bindable
    public abstract boolean isNuancesYellow();

    public abstract void setNuancesYellow(boolean nuancesYellow);

    @Bindable
    public abstract boolean isNuancesGreen();

    public abstract void setNuancesGreen(boolean nuancesGreen);

    @Bindable
    public abstract boolean isNuancesLightBlue();

    public abstract void setNuancesLightBlue(boolean nuancesLightBlue);

    @Bindable
    public abstract boolean isNuancesBlue();

    public abstract void setNuancesBlue(boolean nuancesBlue);

    @Bindable
    public abstract boolean isNuancesViolet();

    public abstract void setNuancesViolet(boolean nuancesViolet);

    @Bindable
    public abstract boolean isNuancesBrown();

    public abstract void setNuancesBrown(boolean nuancesBrown);

    @Bindable
    public abstract boolean isNuancesBlack();

    public abstract void setNuancesBlack(boolean nuancesBlack);

    @Bindable
    public abstract boolean isNuancesWhite();

    public abstract void setNuancesWhite(boolean nuancesWhite);

    @Bindable
    public abstract boolean isDecorSimple();

    public abstract void setDecorSimple(boolean decorSimple);

    @Bindable
    public abstract boolean isDecorHoliday();

    public abstract void setDecorHoliday(boolean decorHoliday);

    @Bindable
    public abstract boolean isTemperatureHot();

    public abstract void setTemperatureHot(boolean temperatureHot);

    @Bindable
    public abstract boolean isTemperatureMiddle();

    public abstract void setTemperatureMiddle(boolean temperatureMiddle);

    @Bindable
    public abstract boolean isTemperatureCold();

    public abstract void setTemperatureCold(boolean temperatureCold);

    @Bindable
    public abstract boolean isLightNatural();

    public abstract void setLightNatural(boolean lightNatural);

    @Bindable
    public abstract boolean isLightSynthetic();

    public abstract void setLightSynthetic(boolean lightSynthetic);

    @Bindable
    public abstract boolean isLightMixed();

    public abstract void setLightMixed(boolean lightMixed);

    @Bindable
    public abstract boolean isLightDirDirect();

    public abstract void setLightDirDirect(boolean lightDirDirect);

    @Bindable
    public abstract boolean isLightDirBack();

    public abstract void setLightDirBack(boolean lightDirBack);

    @Bindable
    public abstract boolean isLightDirSide();

    public abstract void setLightDirSide(boolean lightDirSide);

    @Bindable
    public abstract boolean isLightDirMixed();

    public abstract void setLightDirMixed(boolean lightDirMixed);

    @Bindable
    public abstract boolean isLightCount1();

    public abstract void setLightCount1(boolean lightCount1);

    @Bindable
    public abstract boolean isLightCount2();

    public abstract void setLightCount2(boolean lightCount2);

    @Bindable
    public abstract boolean isLightCount3();

    public abstract void setLightCount3(boolean lightCount3);
}
