package ru.dmisb.photon.screens.new_card;

import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import ru.dmisb.photon.BR;
import ru.dmisb.photon.core.BaseFilterViewModel;
import ru.dmisb.photon.data.enums.Decor;
import ru.dmisb.photon.data.enums.Dish;
import ru.dmisb.photon.data.enums.Light;
import ru.dmisb.photon.data.enums.LightDirection;
import ru.dmisb.photon.data.enums.LightSource;
import ru.dmisb.photon.data.enums.Nuances;
import ru.dmisb.photon.data.enums.Temperature;

@SuppressWarnings("unused")
public class NewCardViewModel extends BaseFilterViewModel {
    private String title;
    private String tag;
    private List<String> tags = new ArrayList<>();
    private int albumCount;

    private Dish dish;

    private boolean nuancesRed;
    private boolean nuancesOrange;
    private boolean nuancesYellow;
    private boolean nuancesGreen;
    private boolean nuancesLightBlue;
    private boolean nuancesBlue;
    private boolean nuancesViolet;
    private boolean nuancesBrown;
    private boolean nuancesBlack;
    private boolean nuancesWhite;

    private Decor decor;
    private Temperature temperature;
    private Light light;
    private LightDirection lightDirection;
    private LightSource lightSource;

    NewCardViewModel() {
        dish = Dish.meat;
        decor = Decor.empty;
        temperature = Temperature.empty;
        light = Light.empty;
        lightDirection = LightDirection.empty;
        lightSource = LightSource.empty;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getTag() {
        return tag;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setTag(String tag) {
        this.tag = tag;
        notifyPropertyChanged(BR.tag);
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    List<String> getTags() {
        return tags;
    }

    @Bindable
    public int getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
        notifyChange();
    }

    @Override
    public boolean isMeat() {
        return dish == Dish.meat;
    }

    @Override
    public void setMeat(boolean meat) {
        if (meat)
            dish = Dish.meat;
        notifyChange();
    }

    @Override
    public boolean isFish() {
        return dish == Dish.fish;
    }

    @Override
    public void setFish(boolean fish) {
        if (fish)
            dish = Dish.fish;
        notifyChange();
    }

    @Override
    public boolean isVegetables() {
        return dish == Dish.vegetables;
    }

    @Override
    public void setVegetables(boolean vegetables) {
        if (vegetables)
            dish = Dish.vegetables;
        notifyChange();
    }

    @Override
    public boolean isFruit() {
        return dish == Dish.fruit;
    }

    @Override
    public void setFruit(boolean fruit) {
        if (fruit)
            dish = Dish.fruit;
        notifyChange();
    }

    @Override
    public boolean isCheese() {
        return dish == Dish.cheese;
    }

    @Override
    public void setCheese(boolean cheese) {
        if (cheese)
            dish = Dish.cheese;
        notifyChange();
    }

    @Override
    public boolean isDesert() {
        return dish == Dish.dessert;
    }

    @Override
    public void setDesert(boolean desert) {
        if (desert)
            dish = Dish.dessert;
        notifyChange();
    }

    @Override
    public boolean isDrinks() {
        return dish == Dish.drinks;
    }

    @Override
    public void setDrinks(boolean drinks) {
        if (drinks)
            dish = Dish.drinks;
        notifyChange();
    }

    @Override
    public boolean isNuancesRed() {
        return nuancesRed;
    }

    @Override
    public void setNuancesRed(boolean nuancesRed) {
        this.nuancesRed = nuancesRed;
        notifyChange();
    }

    @Override
    public boolean isNuancesOrange() {
        return nuancesOrange;
    }

    @Override
    public void setNuancesOrange(boolean nuancesOrange) {
        this.nuancesOrange = nuancesOrange;
        notifyChange();
    }

    @Override
    public boolean isNuancesYellow() {
        return nuancesYellow;
    }

    @Override
    public void setNuancesYellow(boolean nuancesYellow) {
        this.nuancesYellow = nuancesYellow;
        notifyChange();
    }

    @Override
    public boolean isNuancesGreen() {
        return nuancesGreen;
    }

    @Override
    public void setNuancesGreen(boolean nuancesGreen) {
        this.nuancesGreen = nuancesGreen;
        notifyChange();
    }

    @Override
    public boolean isNuancesLightBlue() {
        return nuancesLightBlue;
    }

    @Override
    public void setNuancesLightBlue(boolean nuancesLightBlue) {
        this.nuancesLightBlue = nuancesLightBlue;
        notifyChange();
    }

    @Override
    public boolean isNuancesBlue() {
        return nuancesBlue;
    }

    @Override
    public void setNuancesBlue(boolean nuancesBlue) {
        this.nuancesBlue = nuancesBlue;
        notifyChange();
    }

    @Override
    public boolean isNuancesViolet() {
        return nuancesViolet;
    }

    @Override
    public void setNuancesViolet(boolean nuancesViolet) {
        this.nuancesViolet = nuancesViolet;
        notifyChange();
    }

    @Override
    public boolean isNuancesBrown() {
        return nuancesBrown;
    }

    @Override
    public void setNuancesBrown(boolean nuancesBrown) {
        this.nuancesBrown = nuancesBrown;
        notifyChange();
    }

    @Override
    public boolean isNuancesBlack() {
        return nuancesBlack;
    }

    @Override
    public void setNuancesBlack(boolean nuancesBlack) {
        this.nuancesBlack = nuancesBlack;
        notifyChange();
    }

    @Override
    public boolean isNuancesWhite() {
        return nuancesWhite;
    }

    @Override
    public void setNuancesWhite(boolean nuancesWhite) {
        this.nuancesWhite = nuancesWhite;
        notifyChange();
    }

    @Override
    public boolean isDecorSimple() {
        return decor == Decor.simple;
    }

    @Override
    public void setDecorSimple(boolean decorSimple) {
        if (decorSimple)
            decor = Decor.simple;
        else
            if (decor == Decor.simple)
                decor = Decor.empty;

        notifyChange();
    }

    @Override
    public boolean isDecorHoliday() {
        return decor == Decor.holiday;
    }

    @Override
    public void setDecorHoliday(boolean decorHoliday) {
        if (decorHoliday)
            decor = Decor.holiday;
        else
            if (decor == Decor.holiday)
                decor = Decor.empty;

        notifyChange();
    }

    @Override
    public boolean isTemperatureHot() {
        return temperature == Temperature.hot;
    }

    @Override
    public void setTemperatureHot(boolean temperatureHot) {
        if (temperatureHot)
            temperature = Temperature.hot;
        else
            if (temperature == Temperature.hot)
                temperature = Temperature.empty;

        notifyChange();
    }

    @Override
    public boolean isTemperatureMiddle() {
        return temperature == Temperature.middle;
    }

    @Override
    public void setTemperatureMiddle(boolean temperatureMiddle) {
        if (temperatureMiddle)
            temperature = Temperature.middle;
        else
        if (temperature == Temperature.middle)
            temperature = Temperature.empty;

        notifyChange();
    }

    @Override
    public boolean isTemperatureCold() {
        return temperature == Temperature.cold;
    }

    @Override
    public void setTemperatureCold(boolean temperatureCold) {
        if (temperatureCold)
            temperature = Temperature.cold;
        else
        if (temperature == Temperature.cold)
            temperature = Temperature.empty;

        notifyChange();
    }

    @Override
    public boolean isLightNatural() {
        return light == Light.natural;
    }

    @Override
    public void setLightNatural(boolean lightNatural) {
        if (lightNatural)
            light = Light.natural;
        else
        if (light == Light.natural)
            light = Light.empty;

        notifyChange();
    }

    @Override
    public boolean isLightSynthetic() {
        return light == Light.synthetic;
    }

    @Override
    public void setLightSynthetic(boolean lightSynthetic) {
        if (lightSynthetic)
            light = Light.synthetic;
        else
        if (light == Light.synthetic)
            light = Light.empty;

        notifyChange();
    }

    @Override
    public boolean isLightMixed() {
        return light == Light.mixed;
    }

    @Override
    public void setLightMixed(boolean lightMixed) {
        if (lightMixed)
            light = Light.mixed;
        else
        if (light == Light.mixed)
            light = Light.empty;

        notifyChange();
    }

    @Override
    public boolean isLightDirDirect() {
        return lightDirection == LightDirection.direct;
    }

    @Override
    public void setLightDirDirect(boolean lightDirDirect) {
        if (lightDirDirect)
            lightDirection = LightDirection.direct;
        else
        if (lightDirection == LightDirection.direct)
            lightDirection = LightDirection.empty;

        notifyChange();
    }

    @Override
    public boolean isLightDirBack() {
        return lightDirection == LightDirection.backlight;
    }

    @Override
    public void setLightDirBack(boolean lightDirBack) {
        if (lightDirBack)
            lightDirection = LightDirection.backlight;
        else
        if (lightDirection == LightDirection.backlight)
            lightDirection = LightDirection.empty;

        notifyChange();
    }

    @Override
    public boolean isLightDirSide() {
        return lightDirection == LightDirection.sideLight;
    }

    @Override
    public void setLightDirSide(boolean lightDirSide) {
        if (lightDirSide)
            lightDirection = LightDirection.sideLight;
        else
        if (lightDirection == LightDirection.sideLight)
            lightDirection = LightDirection.empty;

        notifyChange();
    }

    @Override
    public boolean isLightDirMixed() {
        return lightDirection == LightDirection.mixed;
    }

    @Override
    public void setLightDirMixed(boolean lightDirMixed) {
        if (lightDirMixed)
            lightDirection = LightDirection.mixed;
        else
        if (lightDirection == LightDirection.mixed)
            lightDirection = LightDirection.empty;

        notifyChange();
    }

    @Override
    public boolean isLightCount1() {
        return lightSource == LightSource.one;
    }

    @Override
    public void setLightCount1(boolean lightCount1) {
        if (lightCount1)
            lightSource = LightSource.one;
        else
        if (lightSource == LightSource.one)
            lightSource = LightSource.empty;

        notifyChange();
    }

    @Override
    public boolean isLightCount2() {
        return lightSource == LightSource.two;
    }

    @Override
    public void setLightCount2(boolean lightCount2) {
        if (lightCount2)
            lightSource = LightSource.two;
        else
        if (lightSource == LightSource.two)
            lightSource = LightSource.empty;

        notifyChange();
    }

    @Override
    public boolean isLightCount3() {
        return lightSource == LightSource.three;
    }

    @Override
    public void setLightCount3(boolean lightCount3) {
        if (lightCount3)
            lightSource = LightSource.three;
        else
        if (lightSource == LightSource.three)
            lightSource = LightSource.empty;

        notifyChange();
    }

    String getDish() {
        return dish.toString();
    }

    String getNuances() {
        StringBuilder builder = new StringBuilder();
        if (nuancesRed)
            builder.append(Nuances.red.name()).append(',');
        if (nuancesOrange)
            builder.append(Nuances.orange.name()).append(',');
        if (nuancesYellow)
            builder.append(Nuances.yellow.name()).append(',');
        if (nuancesGreen)
            builder.append(Nuances.green.name()).append(',');
        if (nuancesLightBlue)
            builder.append(Nuances.lightBlue.name()).append(',');
        if (nuancesBlue)
            builder.append(Nuances.blue.name()).append(',');
        if (nuancesViolet)
            builder.append(Nuances.violet.name()).append(',');
        if (nuancesBrown)
            builder.append(Nuances.brown.name()).append(',');
        if (nuancesBlack)
            builder.append(Nuances.black.name()).append(',');
        if (nuancesWhite)
            builder.append(Nuances.white.name()).append(',');
        return builder.toString();
    }

    String getDecor() {
        return decor.toString();
    }

    String getTemperature() {
        return temperature.toString();
    }

    String getLight() {
        return light.toString();
    }

    String getLightDirection() {
        return lightDirection.toString();
    }

    String getLightSource() {
        return lightSource.toString();
    }
}
