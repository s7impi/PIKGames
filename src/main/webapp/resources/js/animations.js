var boardgameAnimations = angular.module('boardgameAnimations', ['ngAnimate']);

boardgameAnimations.animation('.boardgame', function() {

    var animateUp = function(element, className, done) {alert("UP");
        if(className != 'active') {
            return;
        }
        element.css({
            position: 'absolute',
            top: 500,
            left: 0,
            display: 'block'
        });

        jQuery(element).animate({
            top: 0
        }, done);

        return function(cancel) {
            if(cancel) {
                element.stop();
            }
        };
    }

    var animateDown = function(element, className, done) {alert("DOWN");
        if(className != 'active') {
            return;
        }
        element.css({
            position: 'absolute',
            left: 0,
            top: 0
        });

        jQuery(element).animate({
            top: -500
        }, done);

        return function(cancel) {
            if(cancel) {
                element.stop();
            }
        };
    }

    return {
        addClass: animateUp,
        removeClass: animateDown
    };
});
