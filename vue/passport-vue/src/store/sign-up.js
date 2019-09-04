

function fire_ok(fn) {
    if (fn != null) {
        fn();
    }
}


export default {

    name: 'SignUp',

    namespaced: true,

    state: {
        ticket: ''
    },

    getters: {

    },

    mutations: {
        save_ticket(state, param) {
            state.ticket = param.ticket;
        }
    },

    actions: {

        step1(context, param) {

            var email = param.email;
            var method = 'post';
            var type = 'SignUp';
            var id = null;

            var content = {
                signUp: {
                    email
                }
            };

            var ok = res => {
                var ticket = res.content.signUp.ticket;
                context.commit('save_ticket', { ticket });
                fire_ok(param.ok, res);
            };

            context.dispatch('rest', { method, type, id, content, ok }, { root: true });
        },

        step2(context, param) {

            var verification = param.verification;
            var method = 'put';
            var type = 'SignUp';
            var id = context.state.ticket;

            var content = {
                signUp: {
                    verification,
                }
            };

            var ok = res => {
                fire_ok(param.ok, res);
            };

            context.dispatch('rest', { method, type, id, content, ok }, { root: true });
        },

    },

}
