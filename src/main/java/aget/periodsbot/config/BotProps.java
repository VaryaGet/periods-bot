/*
 * MIT License
 *
 * Copyright (c) 2024 Artem Getmanskii
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package aget.periodsbot.config;

/**
 * Properties for bot.
 *
 * @since 0.1.0
 */
public final class BotProps {
    /**
     * Name.
     */
    private final Prop name;

    /**
     * Secret.
     */
    private final Prop secret;

    public BotProps() {
        this("bot_name", "bot_secret");
    }

    public BotProps(final String namekey, final String secretkey) {
        this(new PropEnv(namekey), new PropEnv(secretkey));
    }

    public BotProps(final Prop name, final Prop secret) {
        this.name = name;
        this.secret = secret;
    }

    public String botName() {
        return this.name.get();
    }

    public String botToken() {
        return this.secret.get();
    }
}
