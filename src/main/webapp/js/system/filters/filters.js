

'use strict';

moduloFiltros

        .filter('clipString', function ($filter)
        {
            return function (input)
            {
                if (input == null) {
                    return "";
                }

                if (input.length > 200) {
                    return input.substr(0, 150).trim() + " ...";

                } else {
                    return input;
                }

            };
        })







        ;
