AchievementBot
==============

This bot monitors a specified channel and awards custom achievements to IRC users. Users can query their achievement scores and compare scores via commands in the chat.

Commands
========

The bot currently allows for the following commands on channel:

!points (username)

The bot informs the channel of the number of points that the specified username has in achievements. If no username is provided, the presumption is that the user is querying his own points.

!help

A link to this page is offered.

These commands can only be used in a private message to the bot:

!list

The bot informs the user of all the achievements that they have won in the following format: Achievement Name - Achievement Value - Achievement Description. This command is restricted to private message to avoid channel flooding.

Achievements
============

A current list of implemented achievements can be found here: https://raw.github.com/jaderain/irc-achievement-bot/master/src/achievements/list.txt
