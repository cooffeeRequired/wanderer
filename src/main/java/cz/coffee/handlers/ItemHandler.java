package cz.coffee.handlers;

import cz.coffee.GameConfig;
import cz.coffee.entities.Hero;
import cz.coffee.facades.GameEntity;
import cz.coffee.items.PotionGreen;
import cz.coffee.items.PotionRed;
import cz.coffee.items.Shield;
import cz.coffee.items.Sword;

import static cz.coffee.GameConfig.ITEMS;
import static cz.coffee.controllers.GameController.playSound;

public class ItemHandler implements Handler<GameEntity>{

    AttributeHandler attributeHandler;

    public ItemHandler() {
        this.attributeHandler = new AttributeHandler();
    }

    @Override
    public boolean handle(GameEntity b) {
        var eLocation = b.getLocation();
        var eDirection = b.getDirection();


        int yOffset = switch (eDirection) {
            case "hero-down" -> GameConfig.TILE_SIZE;
            case "hero-up" -> -GameConfig.TILE_SIZE;
            default -> 0;
        };

        int xOffset = switch (eDirection) {
            case "hero-right" -> GameConfig.TILE_SIZE;
            case "hero-left" -> -GameConfig.TILE_SIZE;
            default -> 0;
        };

        if (b instanceof Hero hero) {
            var iterator = ITEMS.iterator();
            while (iterator.hasNext()) {
                var e = iterator.next();
                if (e.getPositionX() == eLocation.getX() + xOffset && e.getPositionY() == eLocation.getY() + yOffset) {
                    switch (e) {
                        case PotionRed _ -> {
                            hero.redPotion();
                            playSound("item-pickup.au");
                        }
                        case PotionGreen _ -> {
                            hero.greenPotion();
                            playSound("item-pickup.au");
                        }
                        case Shield _ -> {
                            hero.gotShield();
                            playSound("item-equip.au");
                        }
                        case Sword _ -> {
                            hero.gotSword();
                            playSound("item-equip.au");
                        }
                        default -> {
                        }
                    }
                    iterator.remove();
                }
            }
            // TODO add a GUI, what will say press enter for get this effect
            return true;
        }
        return false;
    }

    @Override
    public void dispatchTo(GameEntity b) {

    }

    @Override
    public void mountTo(GameEntity b) {

    }


    // tento handler bude čistě pro práci s itemy
}
