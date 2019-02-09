

'use strict';

moduloFiltros

        .filter('clipString', function ($filter)
        {
            return function (input)
            {
                if (input == null) {
                    return "";
                }

                if (input.length > 100) {
                    return input.substr(0, 90).trim() + " ...";

                } else {
                    return input;
                }

            };
        })







        ;
